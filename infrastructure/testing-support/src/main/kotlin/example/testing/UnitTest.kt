package example.testing

import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.DisplayNameGeneration
import org.junit.jupiter.api.DisplayNameGenerator
import org.junit.jupiter.api.TestInstance
import org.junit.jupiter.api.TestInstance.Lifecycle.PER_CLASS

@TestInstance(PER_CLASS)
@DisplayName("Unit test")
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores::class)
abstract class UnitTest
