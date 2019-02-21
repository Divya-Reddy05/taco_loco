package com.taco.loco.controller;

import com.taco.loco.baseframework.AbstractControllerTest;
import org.junit.Test;
import org.mockito.MockitoAnnotations;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class ApplicationMetadataControllerTest extends AbstractControllerTest<ApplicationMetadataController> {

    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Override
    protected ApplicationMetadataController createController() {
        ApplicationMetadataController controller = new ApplicationMetadataController();
        return controller;
    }

    @Test
    public void testGetVersionSuccess() throws Exception {
        String url = ApiRoute.BASE_MAPPING + ApiRoute.METADATA;

        mockMvc.perform(getWithoutBody(url)
                .header("content-type", "application/json"))
                .andExpect(status().isOk());
    }

}
