package org.mb4j.liferay;

import com.google.common.base.Objects;
import java.io.IOException;
import java.io.OutputStream;
import java.io.Writer;
import javax.portlet.MimeResponse;
import javax.portlet.ResourceResponse;
import org.mb4j.brick.renderer.BrickRenderer;
import org.mb4j.component.Response;

public class PortletViewResponse extends Response {

    private final MimeResponse portletResponse;

    public PortletViewResponse(BrickRenderer renderer, MimeResponse portletResponse) {
        super(renderer);
        this.portletResponse = portletResponse;
    }

    @Override
    public void setContentType(String type) {
        portletResponse.setContentType(type);
    }

    @Override
    public void setContentLength(int len) {
        if (portletResponse instanceof ResourceResponse) {
            ((ResourceResponse) portletResponse).setContentLength(len);
            return;
        }
        throw new RuntimeException("setContentLength() not supported for " + portletResponse);
    }

    @Override
    public void setCharacterEncoding(String encoding) {
        if (portletResponse instanceof ResourceResponse) {
            ((ResourceResponse) portletResponse).setCharacterEncoding(encoding);
            return;
        }
        String current = portletResponse.getCharacterEncoding().toLowerCase();
        String newEnc = encoding.toLowerCase();
        if (!Objects.equal(current, newEnc)) {
            throw new RuntimeException("setCharacterEncoding() not supported for " + portletResponse + "."
                    + " The only accepted value is '" + current + "'" + " but received '" + newEnc + "'.");
        }
    }

    @Override
    public Writer getWriter() {
        try {
            return portletResponse.getWriter();
        } catch (IOException ex) {
            throw new RuntimeException("Failed to getWriter() from " + portletResponse + ": " + ex, ex);
        }
    }

    @Override
    public OutputStream getOutputStream() {
        try {
            return portletResponse.getPortletOutputStream();
        } catch (IOException ex) {
            throw new RuntimeException("Failed to getOutputStream() from " + portletResponse + ": " + ex, ex);
        }
    }
}
