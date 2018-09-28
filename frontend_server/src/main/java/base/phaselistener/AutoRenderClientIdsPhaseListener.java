package base.phaselistener;

import java.util.Locale;

import javax.faces.context.FacesContext;
import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;

import base.backingbean.BKSessionBean;
import base.utils.JSFUtil;

public class AutoRenderClientIdsPhaseListener implements PhaseListener {
	private static final long serialVersionUID = 7597302900085851609L;

	public PhaseId getPhaseId() {
		return PhaseId.RESTORE_VIEW;
	}

	public void beforePhase(PhaseEvent event) {

	}

	public void afterPhase(PhaseEvent event) {
		if (FacesContext.getCurrentInstance() != null) {
			FacesContext.getCurrentInstance().getPartialViewContext().getRenderIds().add("globalMessagePanel");
			FacesContext.getCurrentInstance().getPartialViewContext().getRenderIds().add("autoRenderGlobalPanel");
//			for (String id : FacesContext.getCurrentInstance().getPartialViewContext().getRenderIds()) {
//				System.out.println("***Added RenderId = " + id);
//			}
			autoSettingViewLocal();
		}
	}
	
	private void autoSettingViewLocal() {
		try {
			Object obj = JSFUtil.getFromSession("bkSessionBean");
			if (obj != null) {
				FacesContext.getCurrentInstance().getViewRoot().setLocale(new Locale(((BKSessionBean) obj).getViewLocale()));
			} else {
				FacesContext.getCurrentInstance().getViewRoot()
						.setLocale(FacesContext.getCurrentInstance().getApplication().getDefaultLocale());
			}
		} catch (Exception ex) {
			FacesContext.getCurrentInstance().getViewRoot()
					.setLocale(FacesContext.getCurrentInstance().getApplication().getDefaultLocale());
		}
//		System.out.println("***Current View Locale = " + FacesContext.getCurrentInstance().getViewRoot().getLocale().toString());
	}
}