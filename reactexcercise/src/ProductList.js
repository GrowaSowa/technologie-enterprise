import { useState } from "react";
import "axios";
import { Link } from "react-router-dom";

function ProductList({products}) {
	var [filter, setFilter] = useState("");

	function handleFilter() {
		var string = document.getElementsByName("filter")
			.values().next().value.value;
		setFilter(string);
	}

	return (
		<div>
			<h1>List of products</h1>
			<label for="filter">Filter by product title: </label>
			<input name="filter" onChange={handleFilter}/>
			<ul>
				{products.filter((el) => el.title.includes(filter) || filter === "")
					.map((item) => <ProductItem key={item.title} id={item.id} title={item.title} brand={item.brand}/>)}
			</ul>
		</div>
	);
}

function ProductItem({id, title, brand}) {
	return (
		<li><Link to={`details/${id}`}>{title}</Link> ({brand})</li>
	);
}

export default ProductList;