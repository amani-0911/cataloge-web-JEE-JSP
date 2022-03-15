<%@ page import="java.util.List" %>
<%@ page import="com.example.catalogewebjee.metier.Product" %><%
    List<Product> products=(List<Product>)request.getAttribute("listProducts");
      String keyword= request.getAttribute("keyword")!=null?(String) request.getAttribute("keyword"):"";
     Product product=request.getAttribute("product")!=null ?(Product) request.getAttribute("product"):new Product();
       String mode=request.getAttribute("mode")!=null?(String)request.getAttribute("mode"):"";
        %>
<html>
<head>
    <title>Catalogue</title>
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/bootstrap.min.css">
</head>
<body>
<div class="container">
  <div class="d-flex  align-content-around justify-content-end mt-3">
      <form class="form-inline" action="search.do" method="get">
          <fieldset>
              <div class="input-group">

                  <input name="Keyword" type="text" class="form-control" aria-label="Keyword" value="<%=keyword%>">
                  <div class="input-group-append">
                      <button class="btn btn-primary" type="submit">Search</button>
                  </div>
              </div>
          </fieldset>
      </form>
  </div>
    <div class="container ml-auto mt-1">
        <form method="post" action="save.do">
            <div class="form-row align-items-center">
                <div class="col-sm-3 my-1">
                    <label class="sr-only" for="inlineFormInputName">Name</label>
                    <input type="text" class="form-control" name="name" placeholder="Name" id="inlineFormInputName" >
                </div>
                <div class="col-sm-3 my-1">
                    <label class="sr-only" for="inlineFormInputGroupPrice">price</label>

                    <input type="text" class="form-control" name="price" placeholder="Price"  value="0.0" id="inlineFormInputGroupPrice" >
                </div>

                <div class="col-auto my-1">
                    <button type="submit" class="btn btn-success">Save</button>
                </div>
            </div>
        </form>
    </div>
<form method="post" action="update.do">
    <table class="table">
        <thead class="thead-dark">
        <tr>
            <th scope="col">ID</th>
            <th scope="col" >Name</th>
            <th  scope="col">Price</th>
            <th  scope="col">Action</th>
        </tr>
        </thead>
        <tbody>
        <% for (Product p:products) {
        %>
        <% if(mode.equals("edit") && product.getId().equals(p.getId())){%>
        <tr scope="row">

            <td> <input  type="hidden" value="<%= p.getId()%>" name="id"></td>
            <td><input class="form-control" type="text" value="<%=p.getName() %>" name="name"> </td>
            <td><input class="form-control" type="text" value=" <%= p.getPrice()%>" name="price"></td>
            <td class="d-flex ml-1">
                <a onclick="javascript:return confirm('are you sure you want delete this one')"
                   href="delete.do?id=<%=p.getId()%>" class="btn btn-danger">Delete</a>
                <button
                       type="submit" class="ml-1 btn btn-success">Update</button></td>

        </tr>

        <%}else {%>
        <tr scope="row">

            <td> <%= p.getId()%></td>
            <td> <%= p.getName() %></td>
            <td> <%= p.getPrice()%> $</td>
            <td class="d-flex ml-1">
                <a onclick="javascript:return confirm('are you sure you want delete this one')"
                        href="delete.do?id=<%=p.getId()%>" class="btn btn-danger">Delete</a>
            <a
               href="edit.do?id=<%=p.getId()%>" class="ml-1 btn btn-success">Edit</a></td>

        </tr>
        <%}%>
        <% }%>

        </tbody>
    </table>
</form>
</div>
</body>
</html>
