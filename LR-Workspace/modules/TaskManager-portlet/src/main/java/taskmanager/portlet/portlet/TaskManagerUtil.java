package taskmanager.portlet.portlet;

import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.WebKeys;

import javax.portlet.PortletRequest;

/**
 * @author miguel.silva
 *
 */
public class TaskManagerUtil {
	/**
	 * @param portletRequest
	 * @return
	 */
	public static long getCompanyId(PortletRequest portletRequest) {
		ThemeDisplay themeDisplay = (ThemeDisplay) portletRequest.getAttribute(WebKeys.THEME_DISPLAY);
		return themeDisplay.getCompanyId();
	}

	/**
	 * @param portletRequest
	 * @return
	 */
	public static long getGroupId(PortletRequest portletRequest) {
		ThemeDisplay themeDisplay = (ThemeDisplay) portletRequest.getAttribute(WebKeys.THEME_DISPLAY);
		return themeDisplay.getScopeGroupId();
	}

	/**
	 * @param portletRequest
	 * @return
	 */
	public static long getUserId(PortletRequest portletRequest) {
		ThemeDisplay themeDisplay = (ThemeDisplay) portletRequest.getAttribute(WebKeys.THEME_DISPLAY);
		return themeDisplay.getUserId();
	}
}
