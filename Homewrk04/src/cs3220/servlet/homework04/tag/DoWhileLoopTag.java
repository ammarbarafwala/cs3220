package cs3220.servlet.homework04.tag;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.BodyTagSupport;

public class DoWhileLoopTag extends BodyTagSupport {

	private static final long serialVersionUID = 1L;
	boolean condition;
	
	public void setValue(boolean condition) {
		this.condition=condition;
	}
	
	@Override
	public int doStartTag() throws JspException {
		// TODO Auto-generated method stub
			return EVAL_BODY_INCLUDE;
	}
	@Override
	public int doAfterBody() throws JspException {
		// TODO Auto-generated method stub
		if(ConditionTag.v1)
			return EVAL_BODY_AGAIN;
		else
			return SKIP_BODY;
	}
	@Override
	public int doEndTag() throws JspException {
		// TODO Auto-generated method stub
		return super.doEndTag();
	}
}