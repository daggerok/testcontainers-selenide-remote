package example.testing

import io.restassured.RestAssured
import io.restassured.builder.RequestSpecBuilder
import io.restassured.specification.RequestSpecification
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs
import org.springframework.restdocs.RestDocumentationContextProvider
import org.springframework.restdocs.RestDocumentationExtension
import org.springframework.restdocs.operation.preprocess.Preprocessors
import org.springframework.restdocs.restassured3.RestAssuredRestDocumentation
import org.springframework.test.context.junit.jupiter.SpringExtension

@AutoConfigureRestDocs
@DisplayName("REST API Documentation test")
@ExtendWith(RestDocumentationExtension::class, SpringExtension::class)
abstract class RestApiDocumentationTest : IntegrationTest() {

    private lateinit var spec: RequestSpecification

    @BeforeEach
    protected fun setUp(restDocumentation: RestDocumentationContextProvider) {
        spec = RequestSpecBuilder()
            .addFilter(RestAssuredRestDocumentation.documentationConfiguration(restDocumentation))
            .build()
    }

    protected fun of(snippetIdentifier: String, snippetsPort: Int = 8080, snippetsHost: String = "localhost") = run {
        val springRestDocsPort = System.getProperty("snippetsPort", "$snippetsPort").toInt()
        val springRestDocsHost = System.getProperty("snippetsHost", snippetsHost)
        val processor = Preprocessors.modifyUris().host(springRestDocsHost).port(springRestDocsPort)
        val prettyPrint = Preprocessors.prettyPrint()
        val requestProcessor = Preprocessors.preprocessRequest(prettyPrint, processor)
        val responseProcessor = Preprocessors.preprocessResponse(prettyPrint, processor)

        RestAssured.given(spec).filter(
            RestAssuredRestDocumentation.document(
                snippetIdentifier, requestProcessor, responseProcessor
            )
        )
    }
}
