package pl.slowikowski.demo.utils;

import pl.slowikowski.demo.crud.product.Product;
import pl.slowikowski.demo.crud.product.ProductDTO;
import pl.slowikowski.demo.crud.productGroup.ProductGroup;
import pl.slowikowski.demo.crud.productGroup.ProductGroupDTO;

import java.util.HashSet;

public class Utils {

    public static final Long id = 1L;
    public static final String name = "Bike";
    public static final String description = "bike123";
    public static final int price = 11;

    public static final Long secondId = 2L;
    public static final String secondName = "Toy";
    public static final String secondDescription = "toy123";
    public static final int secondPrice = 22;

    public static final Long groupId = 2L;
    public static final String groupName = "Sport";
    public static final String groupDescription = "Sport123";

    public static final Long secondGroupId = 3L;
    public static final String secondGroupName = "Sport";
    public static final String secondGroupDescription = "Sport123";

    public static Product getProduct() {
        return Product.ProductBuilder.aProduct()
                .withId(id)
                .withName(name)
                .withDescription(description)
                .withPrice(price)
                .withGroup(getProductGroup())
                .build();
    }

    public static Product getSecondProduct() {
        return Product.ProductBuilder.aProduct()
                .withId(secondId)
                .withName(secondName)
                .withDescription(secondDescription)
                .withPrice(secondPrice)
                .withGroup(getSecondGroup())
                .build();
    }

    public static ProductDTO getProductDto() {
        return ProductDTO.ProductDTOBuilder.aProductDTO()
                .withId(id)
                .withName(name)
                .withDescription(description)
                .withPrice(price)
                .withGroupId(groupId)
                .build();
    }

    public static ProductDTO getSecondProductDto() {
        return ProductDTO.ProductDTOBuilder.aProductDTO()
                .withId(secondId)
                .withName(secondName)
                .withDescription(secondDescription)
                .withPrice(secondPrice)
                .withGroupId(secondGroupId)
                .build();
    }


    public static ProductGroup getProductGroup() {
        return ProductGroup.ProductGroupBuilder.aProductGroup()
                .withId(groupId)
                .withName(groupName)
                .withDescription(groupDescription)
                .withProducts(new HashSet<>())
                .build();
    }

    public static ProductGroup getSecondGroup() {
        return ProductGroup.ProductGroupBuilder.aProductGroup()
                .withId(secondGroupId)
                .withName(secondGroupName)
                .withDescription(secondGroupDescription)
                .withProducts(new HashSet<>())
                .build();
    }

    public static ProductGroupDTO getProductGroupDto() {
        return ProductGroupDTO.ProductGroupDTOBuilder.aProductGroupDTO()
                .withId(groupId)
                .withName(groupName)
                .withDescription(groupDescription)
                .withProducts(new HashSet<>())
                .build();
    }
}
