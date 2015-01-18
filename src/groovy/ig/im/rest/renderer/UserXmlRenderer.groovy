package ig.im.rest.renderer

import grails.rest.render.AbstractRenderer
import grails.rest.render.RenderContext
import groovy.xml.MarkupBuilder
import ig.canteen.user.User
import org.codehaus.groovy.grails.web.mime.MimeType

/**
 * Created by intelligrape on 29/11/14.
 */
class UserXmlRenderer extends AbstractRenderer<User> {

    UserXmlRenderer() {
        super(User, [MimeType.XML, MimeType.TEXT_XML] as MimeType[])
    }

    @Override
    public void render(User user, RenderContext context) {
        context.contentType = MimeType.XML.name

        def xml = new MarkupBuilder(context.writer)
        xml.user(id: user.id, firstname: user.firstName, lastname: user.lastName, email: user.email, employeeId: user.employeeCode)
    }
}
