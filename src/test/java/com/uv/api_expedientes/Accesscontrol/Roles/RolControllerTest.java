// package com.uv.api_expedientes.Accesscontrol.Roles;

// import com.uv.api_expedientes.AccessControl.Roles.RolController;
// import com.uv.api_expedientes.AccessControl.Roles.RolService;
// import com.uv.api_expedientes.AccessControl.Roles.dtos.IdRolDto;
// import org.junit.jupiter.api.Test;
// import org.mockito.Mockito;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
// import org.springframework.test.web.servlet.MockMvc;
// import org.springframework.http.MediaType;
// import static
// org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
// import static
// org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
// import static
// org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

// @WebMvcTest(RolController.class)
// public class RolControllerTest {

// @Autowired
// private MockMvc mockMvc;

// private RolService rolService;

// @Test
// void testGetMethodName_ReturnsIdRol() throws Exception {
// int id = 1;
// IdRolDto idRol = new IdRolDto();
// idRol.setId(id);

// Mockito.when(rolService.getRolById(id)).thenReturn(idRol);

// mockMvc.perform(post("/roles/{id}", id)
// .contentType(MediaType.APPLICATION_JSON))
// .andExpect(status().isOk())
// .andExpect(content().json("{\"id\":1}"));
// }
// }