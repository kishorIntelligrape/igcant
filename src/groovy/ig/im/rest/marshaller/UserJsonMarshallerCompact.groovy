package ig.im.rest.marshaller

import grails.converters.JSON
import ig.canteen.user.User
import org.codehaus.groovy.grails.web.converters.marshaller.ClosureObjectMarshaller

/**
 * Created by intelligrape on 29/11/14.
 */
class UserJsonMarshallerCompact extends ClosureObjectMarshaller<JSON> {


    static final marshal = { User user ->
        def map = [:]
        map.userId = user.id
        map.email = user.email
        map.employeeId = user.employeeCode
        map
    }

    UserJsonMarshallerCompact() {
        super(User, marshal)
    }
}
