package cs3220.servlet.homework04.tag;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.SimpleTagSupport;

public class ConditionTag extends SimpleTagSupport {

	boolean value;
	static boolean v1;
	
	public boolean isValue() {
		return value;
	}

	public void setValue(boolean value) {
		this.value = value;
	}

	@Override
	public void doTag() throws JspException, IOException {
		// TODO Auto-generated method stub
		v1=value;
	}
}
