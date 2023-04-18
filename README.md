# # ShipmentApp
<p align="justify">
&emsp;&emsp;Idea of this project is to create application that would enable to go through ordering and preparation of package process for some products. Mentioned products would be accessible from stock. Products on stock can be inserted by file import, however in case of products damage, quantity might be updated manually. <br />
&emsp;&emsp;Whole process starts from importing an order or possibly several orders. Each order need to be verified if provided products are available. If validation is correct, order is added to database with its own order number. Order number is returned to the user, right after successful import. <br />
&emsp;&emsp;Later on, there comes package preparation process. Package is prepared based on product availability status and its details are returned afterwards.
<br><br>
&emsp;&emsp;Below you can find UML model I prepared for this project. For simplification, I upload only version for first phase of app, where proces finishes on generating an order.<br><br>
<p align="center">
<img width="760" alt="UML1" src="https://user-images.githubusercontent.com/109034460/232905375-03a6ca54-30cd-49ff-a4b3-d515c70a002f.png"> </p>
</p>
