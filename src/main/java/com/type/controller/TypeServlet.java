package com.type.controller;

import java.io.*;
import java.util.*;

import jakarta.servlet.*;
import jakarta.servlet.http.*;

import com.type.model.*;

public class TypeServlet extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");

		if ("getOne_For_Display".equals(action)) { // 來自select_page.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
			String str = req.getParameter("typeId");
			if (str == null || (str.trim()).length() == 0) {
				errorMsgs.add("請輸入活動類型編號");
			}
			// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req.getRequestDispatcher("/back_end/type/select_page.jsp");
				failureView.forward(req, res);
				return;// 程式中斷
			}

			Integer typeId = null;
			try {
				typeId = Integer.valueOf(str);
			} catch (Exception e) {
				errorMsgs.add("活動類型編號格式不正確");
			}
			// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req.getRequestDispatcher("/back_end/type/select_page.jsp");
				failureView.forward(req, res);
				return;// 程式中斷
			}

			/*************************** 2.開始查詢資料 *****************************************/
			TypeService typeSvc = new TypeService();
			TypeVO typeVO = typeSvc.getOneType(typeId);
			if (typeVO == null) {
				errorMsgs.add("查無資料");
			}
			// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req.getRequestDispatcher("/back_end/type/select_page.jsp");
				failureView.forward(req, res);
				return;// 程式中斷
			}

			/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
			req.setAttribute("typeVO", typeVO); // 資料庫取出的empVO物件,存入req
			String url = "/back_end/type/listOneType.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneEmp.jsp
			successView.forward(req, res);
		}

		if ("getOne_For_Update".equals(action)) { // 來自listAllEmp.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			/*************************** 1.接收請求參數 ****************************************/
			Integer typeId = Integer.valueOf(req.getParameter("typeId"));

			/*************************** 2.開始查詢資料 ****************************************/
			TypeService typeSvc = new TypeService();
			TypeVO typeVO = typeSvc.getOneType(typeId);

			/*************************** 3.查詢完成,準備轉交(Send the Success view) ************/
			req.setAttribute("typeVO", typeVO); // 資料庫取出的empVO物件,存入req
			String url = "/back_end/type/update_type_input.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_emp_input.jsp
			successView.forward(req, res);
		}

		if ("update".equals(action)) { // 來自update_emp_input.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
			Integer typeId = Integer.valueOf(req.getParameter("typeId").trim());

			String typeName = req.getParameter("typeName");
			String typeNameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{1,50}$";
			if (typeName == null || typeName.trim().length() == 0) {
				errorMsgs.add("活動類型名稱: 請勿空白");
			}

			String note = req.getParameter("note").trim();
			if (note == null || note.trim().length() == 0) {
				errorMsgs.add("活動說明請勿空白");
			}

			TypeVO typeVO = new TypeVO();
			typeVO.setTypeName(typeName);
			typeVO.setNote(note);
			typeVO.setTypeId(typeId);

			// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				req.setAttribute("typeVO", typeVO); // 含有輸入格式錯誤的empVO物件,也存入req
				RequestDispatcher failureView = req.getRequestDispatcher("/back_end/type/update_type_input.jsp");
				failureView.forward(req, res);
				return; // 程式中斷
			}

			/*************************** 2.開始修改資料 *****************************************/
			TypeService typeSvc = new TypeService();
			typeVO = typeSvc.updateType(typeId, typeName, note);

			/*************************** 3.修改完成,準備轉交(Send the Success view) *************/
			req.setAttribute("typeVO", typeVO); // 資料庫update成功後,正確的的empVO物件,存入req
			String url = "/back_end/type/listOneType.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneEmp.jsp
			successView.forward(req, res);
		}

		if ("insert".equals(action)) { // 來自addEmp.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			/*********************** 1.接收請求參數 - 輸入格式的錯誤處理 *************************/
			String typeName = req.getParameter("typeName");
			if (typeName == null || typeName.trim().length() == 0) {
				errorMsgs.add("活動類別名稱: 請勿空白");
			} 

			String note = req.getParameter("note").trim();
			if (note == null || note.trim().length() == 0) {
				errorMsgs.add("活動類別說明請勿空白");
			}

			TypeVO typeVO = new TypeVO();
			typeVO.setTypeName(typeName);
			typeVO.setNote(note);

			// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				req.setAttribute("typeVO", typeVO); 
				RequestDispatcher failureView = req.getRequestDispatcher("/back_end/type/addType.jsp");
				failureView.forward(req, res);
				return;
			}

			/*************************** 2.開始新增資料 ***************************************/
			TypeService typeSvc = new TypeService();
			typeVO = typeSvc.addType(typeName, note);

			/*************************** 3.新增完成,準備轉交(Send the Success view) ***********/
			String url = "/back_end/type/listAllType.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllEmp.jsp
			successView.forward(req, res);
		}

	}
}
