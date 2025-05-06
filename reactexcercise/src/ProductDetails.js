import { Link, useParams } from "react-router-dom";


function ProductDetails({products}) {
	const id = useParams()['id'];
	const product = products.filter((el) => el.id == id);
	if(product.length === 0) return null;
	return (
		<div>
			<h1>{product[0].name}</h1>
			Category: {product[0].category}<br/>
			Brand: {product[0].brand}<br/>
			Description: {product[0].description}<br/>
			Price: {product[0].price}<br/>
			<img src={product[0].thumbnail}/><br/>
			<Link to="/">Back</Link>
		</div>
	);
}

export default ProductDetails;