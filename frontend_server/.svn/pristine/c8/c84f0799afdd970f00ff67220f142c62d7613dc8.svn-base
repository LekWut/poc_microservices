package base.phaselistener;

import javax.faces.context.FacesContext;
import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CacheControlPhaseListener implements PhaseListener {
	private static final long serialVersionUID = 719935161396491215L;

	public PhaseId getPhaseId() {
		return PhaseId.RENDER_RESPONSE;
	}

	public void beforePhase(PhaseEvent event) {
		FacesContext facesContext = event.getFacesContext();
		HttpServletRequest origRequest = (HttpServletRequest) facesContext.getExternalContext().getRequest();
		if(origRequest.getServletPath().startsWith("/pages/") || origRequest.getServletPath().startsWith("/mobilePages/")){
			HttpServletResponse response = (HttpServletResponse) facesContext.getExternalContext().getResponse();
			response.addHeader("Pragma", "no-cache");
			response.addHeader("Cache-Control", "no-cache");
			/* Stronger according to blog comment below that references HTTP spec */
			response.addHeader("Cache-Control", "no-store");
			response.addHeader("Cache-Control", "must-revalidate");
			/* some date in the past */
			response.addHeader("Expires", "-1");
//			System.out.println("***Cache-Control for this Response is no-cache");
			/* send an STS header with max-age=0 */
			response.addHeader("Strict-Transport-Security", "max-age=0 ; includeSubDomains");
		}
	}

	public void afterPhase(PhaseEvent event) {
	}

}