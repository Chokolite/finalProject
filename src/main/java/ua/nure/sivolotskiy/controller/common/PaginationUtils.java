package ua.nure.sivolotskiy.controller.common;

import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.HttpServletRequest;

public class PaginationUtils {

    public static int getOffset(HttpServletRequest request) {
        if (StringUtils.isNumeric(request.getParameter("offset"))) {
            int offset = Integer.parseInt(request.getParameter("offset"));
            if (offset > 0) {
                return offset;
            }
        }
        return 0;

    }
}
