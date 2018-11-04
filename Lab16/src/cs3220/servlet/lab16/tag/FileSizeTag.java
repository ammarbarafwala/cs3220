package cs3220.servlet.lab16.tag;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.SimpleTagSupport;

public class FileSizeTag extends SimpleTagSupport {

	int value;

  public FileSizeTag() {
	// TODO Auto-generated constructor stub
	  value=0;
  }

	public void setValue(int value) {
		this.value = value;
	}
	
	@Override
	public void doTag() throws JspException, IOException {
		// TODO Auto-generated method stub
		super.doTag();
		JspWriter out = getJspContext().getOut();
		if(value<Math.pow(2, 10))
			out.print(value+" B");
		else if(value<Math.pow(2, 20))
			out.print(Math.round(value/Math.pow(2, 10))+" KB");
		else
			out.print(Math.round(value/Math.pow(2, 20))+" MB");
	}
}
