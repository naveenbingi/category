package com.retailcommerce.category.controller;

import com.retailcommerce.category.model.Category;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@Api(value = "Get Categories by store")
@RestController
@Slf4j
public class CategoryController {

    @ApiOperation(value = "This API will return the array of products existing in the current store.",
            notes = "This API will return the array of products existing in the current store. ",
            response = Category.class, tags = { "Category", })
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "An object of Catalog details", response = Category.class)})
    @RequestMapping(path= "/catalog/{storeId}/categories", method = GET)
    public List<Category> getCategories(@ApiParam(value = "Store Id",required = true) @PathVariable(name="storeId") String storeId) {
        log.debug("->getCategories existing in the current store for Store id" + storeId);

        return getCategoriesList();
    }

    @ApiOperation(value = "This API will return the array of products existing in the current store.",
            notes = "This API will return the array of products existing in the current store. ",
            response = Category.class, tags = { "Category", })
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "An object of Catalog details", response = Category.class)})
    @RequestMapping(path= "/catalog/{storeId}/category/{id}", method = GET)
    public List<Category> getCategories(@ApiParam(value = "Store Id",required = true) @PathVariable(name="storeId") String storeId,
                                        @ApiParam(value = "Id",required = true) @PathVariable(name="id") String categoryId) {
        log.debug("->getCategories existing in the current store for store id "+ storeId +" and category id " + categoryId);

        return getCategoriesList();
    }

    private List<Category> getCategoriesList() {
        List<Category> categories = new ArrayList<>();
        categories.add(new Category("Google", 5, "catalog 1", "https://google.com"));
        categories.add(new Category("Amazon", 5, "catalog 2", "https://amazon.com"));
        categories.add(new Category("Flipkart", 5, "catalog 3", "https://flipcart.com"));

        return categories;
    }
}
