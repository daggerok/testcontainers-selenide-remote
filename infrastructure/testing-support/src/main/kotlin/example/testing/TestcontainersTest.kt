package example.testing

import com.codeborne.selenide.Selenide
import com.codeborne.selenide.WebDriverRunner
import java.io.File
import java.nio.file.Files
import java.nio.file.Path
import java.nio.file.Paths
import org.junit.jupiter.api.AfterAll
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Tag
import org.openqa.selenium.chrome.ChromeOptions
import org.testcontainers.containers.BrowserWebDriverContainer
import org.testcontainers.containers.BrowserWebDriverContainer.VncRecordingMode.RECORD_ALL
import org.testcontainers.containers.Network
import org.testcontainers.containers.VncRecordingContainer.VncRecordingFormat.MP4
import org.testcontainers.junit.jupiter.Container
import org.testcontainers.junit.jupiter.Testcontainers

/**
 * https://github.com/selenide-examples/testcontainers/blob/master/src/test/java/org/selenide/examples/DownloadTestWithDockerAndProxy.java
 * https://www.testcontainers.org/features/networking/#exposing-host-ports-to-the-container
 * https://testcontainers.slack.com/archives/C1SUBPZK6/p1628065705024900
 */
@Tag("e2e")
@Testcontainers
@DisplayName("Test containers test")
abstract class TestcontainersTest(val port: Int) : WireMockTest() {

    protected val buildDirPath: Path = Paths.get(".", "target")
    protected val buildDir: File = buildDirPath.toFile()
    protected val network: Network = Network.newNetwork()

    @BeforeAll
    protected fun beforeAll() {
        org.testcontainers.Testcontainers.exposeHostPorts(port)
    }

    @Container
    protected val chrome: BrowserWebDriverContainer<*> =
        BrowserWebDriverContainer<BrowserWebDriverContainer<*>>(/*"selenium/standalone-chrome-debug:3.141.59"*/)
            .withNetwork(network)
            .withNetworkAliases("chrome")
            .withRecordingMode(RECORD_ALL, buildDir, MP4)
            .withCapabilities(ChromeOptions())

    protected var baseUrl = "http://127.0.0.1:$port"

    @BeforeEach
    protected fun beforeEach() {
        if (Files.notExists(buildDirPath)) buildDir.mkdirs()
        if (System.getenv()["DISABLE_REMOTE"]?.isNotBlank() == true) return
        if (System.getProperty("disableRemote")?.isNotBlank() == true) return
        baseUrl = baseUrl.replaceFirst("127.0.0.1", "host.testcontainers.internal")
        WebDriverRunner.setWebDriver(chrome.webDriver)
    }

    @AfterAll
    protected fun afterAll() {
        Selenide.closeWebDriver()
    }
}
