package com.tarefas.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TarefasApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(TarefasApiApplication.class, args);
	}

	/**
	 * TODO List:
	 * 0. Testar CRUD de Projeto e Tarefa (OK)
	 * 1. DTO (OK)
	 * 2. Lógica Adicional (+ streams) (OK)
	 * 3. Finder methods (OK)
	 * 4. Swagger UI  (OK)
	 * 5. Tratamento de exceções (handler)
	 * 6. Paginação  (OK)
	 * 7. spring Validation  (OK)
	 */

}
