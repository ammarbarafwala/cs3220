package cs3220.servlet.lab15;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cs3220.servlet.lab15.model.Reservation;

/**
 * Servlet implementation class DeleteReservation
 */
@WebServlet("/DeleteReservation")
public class DeleteReservation extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String time=request.getParameter("time");
		String day=request.getParameter("day");
		List<Reservation> list=(List<Reservation>)getServletContext().getAttribute("lab15List");
		for(Reservation r:list){
			if(r.getDay().equals(day) && r.getTime().equals(time)){
				list.remove(r);
				break;
			}
		}
		request.getRequestDispatcher("/WEB-INF/RoomReservations.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
