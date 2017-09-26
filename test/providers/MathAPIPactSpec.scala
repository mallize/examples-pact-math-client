package providers

import org.scalatest.{FunSpec, Matchers}
import service.MathAPIClient

class MathAPIPactSpec extends FunSpec with Matchers {

  import com.itv.scalapact.ScalaPactForger._

  describe("Connecting to the Provider service") {

    it("should be able to sum a list of numbers") {

      forgePact
          .between("math-client")
          .and("math-api")
          .addInteraction(
            interaction
                .description("Calling Sum")
                .given("No State")
                .uponReceiving("/sum?numbers=1,2,3")
                .willRespondWith(200, "sum: 6.0")
          )
          .runConsumerTest { mockConfig =>

            MathAPIClient.sum(mockConfig.baseUrl, "1,2,3").fold(
              e => fail(s"Did not succeed: $e.message"),
              s => s.message shouldEqual "sum: 6.0"
            )
          }
    }

    it("should display a usage messages on bad input") {

      forgePact
          .between("math-client")
          .and("math-api")
          .addInteraction(
            interaction
                .description("Calling Sum")
                .given("No State")
                .uponReceiving("/sum?numbers=1,2,3,A")
                .willRespondWith(400, "Bad request. Example Usage http://host/sum?numbers=1,2,1,3")
          )
          .runConsumerTest { mockConfig =>

            MathAPIClient.sum(mockConfig.baseUrl, "1,2,3,A").fold(
              e => e.message shouldEqual "Bad request. Example Usage http://host/sum?numbers=1,2,1,3",
              _ => fail("Should have failed")
            )

          }
    }
  }
}
