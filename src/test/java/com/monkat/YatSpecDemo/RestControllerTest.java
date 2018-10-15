package com.monkat.YatSpecDemo;

import com.googlecode.yatspec.junit.SpecRunner;
import com.googlecode.yatspec.state.givenwhenthen.TestState;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.rules.SpringClassRule;
import org.springframework.test.context.junit4.rules.SpringMethodRule;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpecRunner.class)
@WebMvcTest(MyRestController.class)
public class RestControllerTest extends TestState {

    @Rule
    public MockitoRule rule = MockitoJUnit.rule();

    @ClassRule
    public static final SpringClassRule springClassRule = new SpringClassRule();

    @Rule
    public final SpringMethodRule springMethodRule = new SpringMethodRule();

    @Autowired
    private MockMvc mvc;

    @Mock
    private BackEndResult mockResult;

    @MockBean
    private BackendService backendService;
    private String page;

    @Test
    public void expectsHelloWorld() throws Exception {

        givenUserRequests("/index");

        whenBackendServiceCalledReturnsMessage("Hello World");

        thenResponseIs("Hello World");

    }

    @Test
    public void expectsAnotherWorld() throws Exception {

        givenUserRequests("/index");

        whenBackendServiceCalledReturnsMessage("Another World");

        thenResponseIs("Another Worldx");

    }

     private void thenResponseIs(final String expects) throws Exception {
        mvc.perform(get(page)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("message", is(expects)));
    }

    private void givenUserRequests(final String page) {
        this.page = page;
    }

    private void whenBackendServiceCalledReturnsMessage(String message) {
        Mockito.when(mockResult.getMessage()).thenReturn(message);
        Mockito.when(backendService.getResult()).thenReturn(mockResult);
        log("Message", message);
    }

}