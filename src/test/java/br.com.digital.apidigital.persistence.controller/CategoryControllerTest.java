package br.com.digital.apidigital.persistence.controller;

import br.com.digital.apidigital.controller.CategoryController;
import br.com.digital.apidigital.persistence.dto.CategoryDto;
import br.com.digital.apidigital.service.CategoryService;
import org.hamcrest.Matchers;
import org.hamcrest.core.Is;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;

@Transactional
public class CategoryControllerTest {
    private static final String ENDPOINT_URL = "/api/category";
    @InjectMocks
    private CategoryController categoryController;
    @Mock
    private CategoryService categoryService;
    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders
                .standaloneSetup(categoryController)
                //.setCustomArgumentResolvers(new PageableHandlerMethodArgumentResolver())
                //.addFilter(CustomFilter::doFilter)
                .build();
    }

    @Test
    public void findAllByPage() throws Exception {
        Page<CategoryDto> page = new PageImpl<>(Collections.singletonList(CategoryBuilder.getDto()));

        Mockito.when(categoryService.findByCondition(ArgumentMatchers.any(), ArgumentMatchers.any())).thenReturn(page);

        ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders.get(ENDPOINT_URL)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.content", Matchers.hasSize(1)));

        Mockito.verify(categoryService, Mockito.times(1)).findByCondition(ArgumentMatchers.any(), ArgumentMatchers.any());
        Mockito.verifyNoMoreInteractions(categoryService);

    }

    @Test
    public void getById() throws Exception {
        Mockito.when(categoryService.findById(ArgumentMatchers.anyLong())).thenReturn(CategoryBuilder.getDto());

        mockMvc.perform(MockMvcRequestBuilders.get(ENDPOINT_URL + "/1"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content()
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.id", Is.is(1)));
        Mockito.verify(categoryService, Mockito.times(1)).findById(1L);
        Mockito.verifyNoMoreInteractions(categoryService);
    }

    @Test
    public void save() throws Exception {
        Mockito.when(categoryService.save(ArgumentMatchers.any(CategoryDto.class))).thenReturn(CategoryBuilder.getDto());

        mockMvc.perform(
                        MockMvcRequestBuilders.post(ENDPOINT_URL)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(CustomUtils.asJsonString(CategoryBuilder.getDto())))
                .andExpect(MockMvcResultMatchers.status().isCreated());
        Mockito.verify(categoryService, Mockito.times(1)).save(ArgumentMatchers.any(CategoryDto.class));
        Mockito.verifyNoMoreInteractions(categoryService);
    }

    @Test
    public void update() throws Exception {
        Mockito.when(categoryService.update(ArgumentMatchers.any(), ArgumentMatchers.anyLong())).thenReturn(CategoryBuilder.getDto());

        mockMvc.perform(
                        MockMvcRequestBuilders.put(ENDPOINT_URL + "/1")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(CustomUtils.asJsonString(CategoryBuilder.getDto())))
                .andExpect(MockMvcResultMatchers.status().isOk());
        Mockito.verify(categoryService, Mockito.times(1)).update(ArgumentMatchers.any(CategoryDto.class), ArgumentMatchers.anyLong());
        Mockito.verifyNoMoreInteractions(categoryService);
    }

    @Test
    public void delete() throws Exception {
        Mockito.doNothing().when(categoryService).deleteById(ArgumentMatchers.anyLong());
        mockMvc.perform(
                MockMvcRequestBuilders.delete(ENDPOINT_URL + "/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(CustomUtils.asJsonString(CategoryBuilder.getIds()))).andExpect(MockMvcResultMatchers.status().isOk());
        Mockito.verify(categoryService, Mockito.times(1)).deleteById(Mockito.anyLong());
        Mockito.verifyNoMoreInteractions(categoryService);
    }
}