package pl.slowikowski.demo.soap.product;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import pl.slowikowski.demo.crud.product.ProductDTO;
import pl.slowikowski.demo.crud.product.ProductService;
import pl.slowikowski.jakub.soap_example._abstract.GetAllResponse;
import pl.slowikowski.jakub.soap_example.product.*;

@Service
@RequiredArgsConstructor
public class ProductSoapService {

    private final ProductService service;
    private final ProductWebMapper mapper;

    public GetAllResponse getAllProducts(GetAllProductsRequest request) {
        Page<ProductDTO> result = service.getAll(mapper.toPageFromPageXml(request.getPageable()), request.getSearch());
        return mapper.toGetAllResponse(result);
    }

    public ProductXmlObject deleteProduct(DeleteProductRequest request) {
        ProductDTO result = service.delete(request.getId());
        return mapper.toWebObject(result);
    }

    public ProductXmlObject findProduct(FindProductRequest request) {
        ProductDTO result = service.findById(request.getId());
        return mapper.toWebObject(result);
    }

    public GetAllResponse productsInGroup(ProductsInGroupRequest request) {
        Page<ProductDTO> result = service.findAllByGroupId(request.getId(), mapper.toPageFromPageXml(request.getPageable()));
        return mapper.toGetAllResponse(result);
    }

    public ProductXmlObject saveProduct(SaveProductRequest request) {
        var productDTO = mapper.toDtoFromSaveRequest(request);
        ProductDTO result = service.save(productDTO);
        return mapper.toWebObject(result);
    }

    public ProductXmlObject buyProduct(BuyProductRequest request) {
        ProductDTO result = service.buyProduct(request.getId());
        return mapper.toWebObject(result);
    }

    public ProductXmlObject updateProduct(ProductXmlObject request) {
        var productDTO = mapper.toDto(request);
        ProductDTO result = service.update(productDTO.getId(), productDTO);
        return mapper.toWebObject(result);
    }

}
