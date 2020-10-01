package mystore.product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("ProductService")
public class ProductService {
    @Autowired
    ProductRepository productRepository;

    public Iterable<Product> getAllProducts(){
        return productRepository.findAll();
    }

    public Product addProduct(Product product){
        if (productRepository.findByProductName(product.getProductName()).orElse(null) == null){
            Product newProduct = new Product();
            newProduct.setProductTypeId(product.getProductTypeId());
            newProduct.setListPrice(product.getListPrice());
            newProduct.setProductName(product.getProductName());
            newProduct.setImageUrl(product.getImageUrl());
            newProduct.setStocks(product.getStocks());
            return productRepository.save(newProduct);
        }
        else{
            return null;
        }
    }

    public Product updateProduct(Product productInDb, Product newProduct){
        if(productInDb.getSku().equals(newProduct.getSku())){
            productInDb.setProductTypeId(newProduct.getProductTypeId());
            productInDb.setListPrice(newProduct.getListPrice());
            productInDb.setProductName(newProduct.getProductName());
            productInDb.setImageUrl(newProduct.getImageUrl());
            productInDb.setStocks(newProduct.getStocks());
            return productRepository.save(productInDb);
        }
        //sku number does not match
        else return null;
    }

    public Product findProductById(Long productId){
        return productRepository.findById(productId).orElse(null);
    }

}
