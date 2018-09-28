package base.phaselistener;

import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import base.utils.JSFUtil;

public class AllPhaseListener implements PhaseListener {
	private static final long serialVersionUID = 1143109372551910163L;
	private static Logger log = LogManager.getLogger(AllPhaseListener.class);

	public PhaseId getPhaseId() {
		return PhaseId.ANY_PHASE;
	}

	public void beforePhase(PhaseEvent e) {
//		System.out.println("beforePhase : " + e.getPhaseId());
		if (e.getPhaseId().equals(PhaseId.RESTORE_VIEW)) {
			if (log.isDebugEnabled()) {
				System.out.println("");/* ทำให้ log file ของบาง Application server อ่านง่ายกว่าใส่ \n */
				System.out.println("beforePhase RESTORE_VIEW : " + JSFUtil.getFullRequestURL());
			}
		}
	}

	public void afterPhase(PhaseEvent e) {
//		System.out.println("afterPhase : " + e.getPhaseId());
		if (e.getPhaseId().equals(PhaseId.RENDER_RESPONSE)) {
			if (log.isDebugEnabled()) {
				System.out.println("afterPhase RENDER_RESPONSE : " + JSFUtil.getFullRequestURL() + "\n");
			}
		}
	}

}
