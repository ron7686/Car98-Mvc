<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<html>

<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <style>
        .container-height {
            height: 500px;
        }

        .row-width {
            width: 100%;
        }

        body {
            background-image: url(../image/Desktop.png);
            background-position: center;
            background-attachment: fixed;
            background-repeat: no-repeat;
            background-size: cover;
        }

        .footer-bottom {
            margin-top: 1em;
            padding-top: 10px;
            padding-bottom: 5px;

        }

        .footer-bottom p.pull-left {
            padding-top: 6px;
            font-size: 0.5em;
            display: flex;
            align-items: center;
            justify-content: center;
        }
    </style>
    <title>Product</title>
</head>

<body>
    <!-- navbar -->
    <jsp:include page="/fragment/topIndex.jsp" />
    <div class="container d-flex text-white container-height">
        <div class="row justify-content-center align-items-center row-width mx-auto">
            <div class="col-md-6">
                <img width='300' height='300' src="<c:url value='comm/picture/${product.bidId}'/>" />
            </div>
            <div class="col-md-6">
                <div>
                    <h2>產品資料</h2>
                    <h3>${product.bidItemName}</h3>
                    <p>賣家ID: ${product.memberBean.name}</p>
                    <p>單價: ${product.bidPrice}</p>
                    <p>商品分類: ${product.bidItemBean.bidCategory}</p>
                    <p>上架時間: ${product.bidTime}</p>
                    <p>商品內容: ${product.bidFormat}</p>
                    <p>
                        <strong>商品編號: </strong> <span class='label label-warning'>
                            ${product.bidId} </span>
                    </p>
                </div>
                <div>
                    
                        <a href="<spring:url value='/comm/products' />" class="btn btn-secondary">
                            <span class="glyphicon-hand-left glyphicon text-white">返回</span>
                        </a>
                    <FORM action="<c:url value='BuyBid.do' />" method="POST">
                        <input name='qty' type="hidden" value='1' />
                        <Input type='hidden' name='bidId' value='${product.bidId}'>
                     
                        <Input class='btn btn-warning btn-large' type='submit' value='加入購物車'>
                       
                        
                    </FORM>
                    
                </div>
            </div>
        </div>
    </div>

    <!-- footer -->
    <jsp:include page="/fragment/footer.jsp" />
</body>

</html>