package ig.canteen.user

import grails.plugin.springsecurity.annotation.Secured
import ig.im.utils.IgCanteenConstants

@Secured([IgCanteenConstants.ROLE_ADMIN])
class UserController {

    static scaffold = true

}
