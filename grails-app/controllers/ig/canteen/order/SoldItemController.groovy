package ig.canteen.order

import grails.rest.RestfulController
import ig.canteen.user.User

class SoldItemController extends RestfulController<SoldItem> {

   static responseFormats = ['json', 'xml', 'hal']

   SoldItemController() {
      super(SoldItem)
   }

   /**
    * List all of resource based on parameters
    *
    * @return List of resources or empty if it doesn't exist
    */
   protected List<SoldItem> listAllResources(Map params) {
      SoldItem.findAllByUser(User.findByToken(params.userToken), params)
   }


}
