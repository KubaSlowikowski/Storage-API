package pl.slowikowski.demo.utils;

import pl.slowikowski.demo.entity.Product;
import pl.slowikowski.demo.entity.ProductGroup;
import pl.slowikowski.demo.model.ProductDTO;
import pl.slowikowski.demo.model.ProductGroupDTO;

public class Utils {

    public static final int id = 1;
    public static final String name = "Bike";
    public static final String description = "bike123";
    public static final int price = 11;

    public static final int secondId = 2;
    public static final String secondName = "Toy";
    public static final String secondDescription = "toy123";
    public static final int secondPrice = 22;

    public static final int groupId = 1;
    public static final String groupName = "Sport";
    public static final String groupDescription = "Sport123";

    public static final int secondGroupId = 2;
    public static final String secondGroupName = "Sport";
    public static final String secondGroupDescription = "Sport123";

    public static Product getProduct() {
        return Product.ProductBuilder.aProduct()
                .withId(id)
                .withName(name)
                .withDescription(description)
                .withPrice(price)
                .build();
    }

    public static Product getSecondProduct() {
        return Product.ProductBuilder.aProduct()
                .withId(secondId)
                .withName(secondName)
                .withDescription(secondDescription)
                .withPrice(secondPrice)
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
                .build();
    }

    public static ProductGroup getSecondGroup() {
        return ProductGroup.ProductGroupBuilder.aProductGroup()
                .withId(secondGroupId)
                .withName(secondGroupName)
                .withDescription(secondGroupDescription)
                .build();
    }

    public static ProductGroupDTO getProductGroupDto() {
        return ProductGroupDTO.ProductGroupDTOBuilder.aProductGroupDTO()
                .withId(groupId)
                .withName(groupName)
                .withDescription(groupDescription)
                .build();
    }
}
