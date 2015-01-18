package ig.canteen.order

import ig.canteen.user.User

class SoldItem {

    User user
    String productCategory
    Integer quantity
    Double pricePerUnit
    Date dateCreated

    static constraints = {

    }

}
