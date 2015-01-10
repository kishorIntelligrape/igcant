package ig.canteen.user

import grails.test.mixin.TestFor
import spock.lang.Specification
import spock.lang.Unroll

/**
 * See the API for {@link grails.test.mixin.domain.DomainClassUnitTestMixin} for usage instructions
 */
@TestFor(User)
class UserSpec extends Specification {

    def setup() {
    }

    def cleanup() {
    }

    @Unroll
    void "validate user token null allowed constraints"() {
        when:
            User user = new User(firstName: firstName, lastName: lastName, employeeCode: employeeCode, token: token)
            user.save(flush: true)

        then: containError == user.hasErrors()

        where:
        sno | firstName | lastName   | employeeCode | token | containError
        1   | "test"    | "lastName" | "IG001"      | null  | false
        2   | "test"    | "lastName" | "IG001"      | "alsdfjasl"  | false
        3   | "test"    | "lastName" | "IG001"      | ""  | false

    }
}
