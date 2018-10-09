<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<link rel="stylesheet" href="static/css/style.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>

	<script type="text/javascript">
	function hideLink() {
		  var x = document.getElementById('myLink');
		  var b = document.getElementById('btnLink');


		  if (x.style.display !== 'block') {
		    x.style.display = 'block';
		    b.childNodes[0].nodeValue = "Add";

		  } else {
		    x.style.display = 'none';
		    b.childNodes[0].nodeValue = "Add";
		  }
		}
	function hide() {
		  var x = document.getElementById('myLink1');
		  var b = document.getElementById('btnLink1');


		  if (x.style.display !== 'block') {
		    x.style.display = 'block';
		    b.childNodes[0].nodeValue = "Active";

		  } else {
		    x.style.display = 'none';
		    b.childNodes[0].nodeValue = "Active";
		  }
		}
	
	
    </script>	
    
    	
    
    		  <div class="col-lg-12 grid-margin stretch-card">
              <div class="card">
                <div class="card-body">
                  <h4 class="card-title">Tax Table</h4>
                
                  <div class="table-responsive">
                    <table class="table table-dark">
                      <thead>
                        <tr>
                         <th>TaxID</th>
						  <th>TaxName</th>
						  <th>Tax Rate</th>
						  <th>Status</th>
                        </tr>
                      </thead>
                      <tbody>
                        <c:forEach var="s" items="${TAXLIST}">
						<tr>
						<td><c:out value="${s.id}"/> </td>
						<td><c:out value="${s.name}"/></td>
						<td><c:out value="${s.rate}" /></td>
						<td><a href="inactive-${s.id}" id="btnLink1"><c:out value="${s.status}"/></a></td>
						</tr>
						</c:forEach>
                      </tbody>
	
                    </table>
                  </div>
                </div>
              </div>
            </div>
              
                	
			

			<div>
			<div class="btn btn-success mr-2" id="btnLink" onclick="hideLink()">Add</div> <br><br>
			<a href="setinactive.html"  type="submit" class="btn btn-success mr-2" >Inactive</a></div>
			
				<div class="col-12" id="myLink" class="myLink">
                  <div class="card" >
                    <div class="card-body">
                      
                      <form class="forms-sample" method="post" action="addTax">
                        <div class="form-group">
                          <label>Tax Name</label>
                          <input  class="form-control" placeholder="Enter Tax Name" name="taxname">
                        </div>
                        <div class="form-group">
                          <label >Tax Rate</label>
                          <input type="text" class="form-control" placeholder="Tax Rate" name="taxrate">
                        </div>
                        <button type="submit" class="btn btn-success mr-2">Submit</button>
                        <button class="btn btn-light">Cancel</button>
                      </form>
                    </div>
                  </div>
                </div>
                
            
                <br><br><br><br><br><br><br>