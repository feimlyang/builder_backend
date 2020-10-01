package mystore.product.web;

import mystore.core.CoreResponseBody;
import mystore.product.Product;
import mystore.product.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/mystore")
public class ProductController {
    @Autowired
    ProductService productService;

    @GetMapping("/products")
    @CrossOrigin("*")
    public ResponseEntity<CoreResponseBody> getAllProducts() {
        CoreResponseBody res;
        Iterable<Product> products = productService.getAllProducts();
        if (products == null){
            res = new CoreResponseBody(null, "no products", new Exception("invalid resource"));
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(res);
        }
        res = new CoreResponseBody(products, "get all products", null);
        return ResponseEntity.status(HttpStatus.OK).body(res);
    }

    @PostMapping("/product")
    @CrossOrigin("*")
    public ResponseEntity<CoreResponseBody> addProduct(@RequestBody Product product){
        CoreResponseBody res;
        Product saveProduct = productService.addProduct(product);
        if (saveProduct == null){
            res = new CoreResponseBody(saveProduct, "product already exists.", new Exception("product already exists."));
        }
        else{
            res = new CoreResponseBody(saveProduct, "new product successfully added.", null);
        }
        return ResponseEntity.ok(res);
    }
}
