package pl.slowikowski.demo.soap.product;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import pl.slowikowski.demo.crud.product.ProductDTO;
import pl.slowikowski.demo.crud.product.ProductService;
import pl.slowikowski.demo.soap.SoapEndpoint;
import pl.slowikowski.demo.soap.SoapService;
import pl.slowikowski.jakub.soap_example._abstract.GetAllResponse;
import pl.slowikowski.jakub.soap_example.product.*;

import javax.jws.WebService;
import javax.xml.ws.BindingType;


@Service
@SoapEndpoint(publish = "/product")
@WebService(endpointInterface = "pl.slowikowski.jakub.soap_example.product.ProductPort",
        serviceName = "ProductPortService",
        targetNamespace = "http://jakub.slowikowski.pl/soap-example/product",
        portName = "ProductPort")
@BindingType("http://java.sun.com/xml/ns/jaxws/2003/05/soap/bindings/HTTP/")
public class ProductSoapService implements SoapService, ProductPort {

    private final ProductService service;
    private final ProductWebMapper mapper;

    public ProductSoapService(ProductService service, ProductWebMapper mapper) {
        this.service = service;
        this.mapper = mapper;
    }

    @Override
    public GetAllResponse getAllProducts(GetAllProductsRequest request) {
        Page<ProductDTO> result = service.getAll(mapper.toPageFromPageXml(request.getPageable()), request.getSearch());
        return mapper.toGetAllResponse(result);
    }

    @Override
    public ProductXmlObject deleteProduct(DeleteProductRequest request) {
        ProductDTO result = service.delete(request.getId());
        return mapper.toWebObject(result);
    }

    @Override
    public ProductXmlObject findProduct(FindProductRequest request) {
        ProductDTO result = service.findById(request.getId());
        return mapper.toWebObject(result);
    }

    @Override
    public GetAllResponse productsInGroup(ProductsInGroupRequest request) {
        Page<ProductDTO> result = service.findAllByGroupId(request.getId(), mapper.toPageFromPageXml(request.getPageable()));
        return mapper.toGetAllResponse(result);
    }

    @Override
    public ProductXmlObject saveProduct(SaveProductRequest request) {
        var productDTO = mapper.toDtoFromSaveRequest(request);
        ProductDTO result = service.save(productDTO);
        return mapper.toWebObject(result);
    }

    @Override
    public ProductXmlObject buyProduct(BuyProductRequest request) {
        ProductDTO result = service.buyProduct(request.getId());
        return mapper.toWebObject(result);
    }

    @Override
    public ProductXmlObject updateProduct(ProductXmlObject request) {
        var productDTO = mapper.toDto(request);
        ProductDTO result = service.update(productDTO.getId(), productDTO);
        return mapper.toWebObject(result);
    }
}
