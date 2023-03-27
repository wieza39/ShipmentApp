# ShipmentApp

  Idea of this project is to create application that would enable to go through ordering and preparation of package process for some products. Mentioned products would be accessible from stock. Products on stock can be inserted by file import, however in case of products damage, quantity might be updated manually. 
	Whole process starts from importing an order or possibly several orders. Each order need to be verified if provided products are available. If validation is correct, order is added to database with its own order number. Order number is returned to the user, right after successful import. 
	Later on, there comes package preparation process. Package is prepared based on product availability status and its details are returned afterwards. 
