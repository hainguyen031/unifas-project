import React, { useEffect } from 'react';
import { useDispatch, useSelector } from 'react-redux';
import { addToCart } from '../../../feature/cart/cartSlice';
import { getAllProducts, selectProductList, selectLoading, selectError } from '../../../feature/product/productsSlice';

function Product() {
  const dispatch = useDispatch();
  

  const products = useSelector(selectProductList);
  const loading = useSelector(selectLoading);
  const error = useSelector(selectError);

  
  useEffect(() => {
    dispatch(getAllProducts());
  }, [dispatch]);

  const handleAddToCart = (product) => {
    dispatch(addToCart(product));
   
  };

  
  const styles = {
    productsContainer: {
      display: 'flex',
      flexWrap: 'wrap',
      gap: '20px',
      justifyContent: 'space-between',
    },
    product: {
      width: 'calc(25% - 20px)',
      boxSizing: 'border-box',
      border: '1px solid #ddd',
      padding: '10px',
      textAlign: 'center',
    },
    productName: {
      fontSize: '16px',
      fontWeight: 'bold',
    },
    productImage: {
      maxWidth: '50%',
      height: '180px',
      width:'120px'
    },
    productDetails: {
      marginTop: '10px',
    },
    addToCartButton: {
      background: '#3498db',
      color: '#fff',
      padding: '5px 10px',
      cursor: 'pointer',
      border: 'none',
      borderRadius: '5px',
    },
  };


  return (
    <div className="home-container">
    
      {  loading ?  (
        <p>Loading...</p>
      ) : error ? (
        <p>Unexpected error occurred...</p>
      ) : (
        <>
          <div className="products-container" style={styles.productsContainer}>
            {products &&
              products.map((product) => (
                <div key={product.id} className="product" style={styles.product}>
                  <h3 style={styles.productName}>{product.name}</h3>
                  <img src={product.image} alt={product.name} style={styles.productImage} />
                  <div className="details" style={styles.productDetails}>
                    <span>{product.desc}</span>
                    <span className="price">${product.price}</span>
                  </div>
                  <div>     <button onClick={() => handleAddToCart(product)} style={styles.addToCartButton}>
                    Add To Cart
                  </button>
                    </div>
             
                </div>
              ))}
          </div>
        </>
      )}
    </div>
  );
}

export default Product;
