<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
		<div class="col-12">
                  <div class="card">
                    <div class="card-body">
                      <h3 >Add L1 Menu</h3><br><br>
		<form:form class="forms-sample" modelAttribute="l1FormBean" action="l1menu.html" method="post">
		<div class="col-sm-9" >
			<h4>Select Menu Master name</h4><br>
 			<form:select path="mmId" class="form-control">
				<form:option value="NONE" label="--- Select ---"/>
			    <form:options items="${MENUMASTERMAP}"/>
			    
			</form:select> 
		</div>
		<br>
		<div class="col-sm-9">
            <h4>Enter L1 menu name</h4><br>
             <form:input path = "l1MenuName" class="form-control"  placeholder="Enter L1 Menu Name" />
        </div>
        <br>
        <div class="col-sm-9" >
        	<h4>Select Tax from list</h4><br>
 			<form:select path="taxId" class="form-control">
				<form:option value="NONE" label="--- Select ---"/>
			    <form:options items="${TAXMAP}" />
			</form:select>
		</div><br><br>
		<input type="submit" class="btn btn-success mr-2" value="Submit">

		</form:form>
 					</div>
                  </div>
                </div>