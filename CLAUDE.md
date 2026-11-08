# CLAUDE.md — Plan de estudio Java 21 / Spring Boot

> Contexto para Claude Code. Este archivo permite retomar el trabajo desde cualquier equipo.
> Proyecto hermano: `PeaceAndSaveProject` (tiene su propio CLAUDE.md).

## Modo de trabajo (IMPORTANTE)

- **El usuario escribe el código. Claude NO implementa los cambios.**
- Claude da guías paso a paso: qué hacer, por qué, qué investigar y pistas. Solo da la solución completa si el usuario la pide.
- Cuando el usuario pide "revisa el paso N", Claude:
  1. Lee el diff (`git diff main...HEAD`, o el último commit con `git show`).
  2. Señala bugs, malas prácticas y mejoras, explicando el porqué.
  3. Dice si el paso está listo o qué falta, y propone el siguiente.
- Se trabaja con un commit por paso, en la rama `feature/migracion-boot3`.
- Idioma: español.

## Objetivo del usuario

Mejorar en Java y Spring Boot con estos dos proyectos como laboratorio:
- Migrar a Spring Boot 3 y Java 21.
- Refactorizar con buenas prácticas de API REST.
- Estudiar Java avanzado: records, sealed types, pattern matching, hilos y virtual threads, anotaciones propias, reflection.

## Diagnóstico inicial (2026-11-07)

**springboot-platzi**: API de supermercado (productos, categorías, compras). Usa Spring Boot 2.5.2, Java 8, Postgres, MapStruct, Springfox y JWT (jjwt 0.9.1). Separa en capas domain/persistence/web.

Bugs encontrados:
- `persistence/ProductoRepository.java`: a `productoCrudRepository` le falta `@Autowired`, así que lanza NullPointerException. En `CompraRepository.java` pasa lo mismo con `purchaseMapper`.
- El `save()` de los dos repositorios nunca persiste: solo convierte DTO → entidad → DTO.
- `JWTUtil`: la clave `"pl4tz1"` está escrita en el código.
- `SecurityUserDetailsService`: el usuario `edison/{noop}platzi` está escrito en el código.
- `ProductController.getByCategory` responde 201 en un GET.
- `domain/service/UserDetailsService.java` es una clase vacía y su nombre choca con el de Spring.
- `ProductRepostitoryTest` no tiene `@SpringBootTest`, así que `@Autowired` nunca inyecta.
- Hay un error de nombre (`ProductRepostitory`) y nombres mezclados en español e inglés.
- Las credenciales de la BD están en los properties.

**PeaceAndSaveProject**: ver su CLAUDE.md. Es el proyecto principal para Java avanzado porque tiene un flujo de aprobación real.

## Hoja de ruta

1. **Fase 1**: migrar springboot-platzi a Boot 3 y Java 21 (guía abajo). ← EN CURSO
2. **Fase 2**: migrar PeaceAndSave de la misma forma y refactorizar: capa de servicio, records como DTOs, `@RestControllerAdvice` + `ProblemDetail`, corregir el modelo de `PeaceAndSave`, Testcontainers.
3. **Fase 3**: funcionalidades nuevas en PeaceAndSave:
   - Flujo de aprobación con sealed interfaces y pattern matching switch.
   - Validación en paralelo por dependencia con virtual threads o `StructuredTaskScope`.
   - Anotaciones propias (`@Auditable`) con AOP y reflection.
   - Validador propio (`ConstraintValidator`).
4. **Fase 4**: Spring Modulith o eventos de dominio, Actuator y Micrometer, seguridad JWT en PeaceAndSave.

La guía detallada de cada fase se prepara cuando termina la anterior.

## Fase 1: Guía de migración de springboot-platzi

Entorno del usuario: Maven 3.9.11 con JDK 21. El comando `java` del PATH en un equipo apunta al JDK 25.

- [ ] **Paso 0: Preparación.** Crear la rama `feature/migracion-boot3` y correr `./mvnw clean compile` para ver el estado de partida.
- [ ] **Paso 1: Corregir bugs con la versión actual** (antes de migrar, para no mezclar problemas):
  - Inyección por constructor con campos `final` en lugar de `@Autowired` en campo.
  - Que `save()` persista de verdad.
  - Código HTTP correcto en `getByCategory`. Pregunta para pensar: ¿tiene sentido `Optional<List<...>>`?
  - Borrar `UserDetailsService.java`, la clase vacía.
- [ ] **Paso 2: `pom.xml`.**
  - Parent en 3.5.x y `java.version` en 21.
  - Borrar `maven.compiler.source/target` y el `<source>/<target>` del plugin.
  - Quitar los `<version>` de los starters de Spring.
  - Cambiar `validation-api` por `spring-boot-starter-validation`.
  - MapStruct en 1.6.x, sin `mapstruct-jdk8`.
  - devtools con `runtime` + `optional`.
- [ ] **Paso 3: `javax.*` → `jakarta.*`** en persistence, validation y servlet. Entender por qué hubo el cambio (Eclipse Foundation).
- [ ] **Paso 4: Springfox → springdoc** (`springdoc-openapi-starter-webmvc-ui`).
  - Anotaciones: `@ApiOperation` → `@Operation` y `@ApiParam` → `@Parameter`.
  - Bean `OpenAPI` para la información de la API.
- [ ] **Paso 5: Spring Security 6.**
  - Bean `SecurityFilterChain` en lugar de `WebSecurityConfigurerAdapter`.
  - `requestMatchers` con DSL de lambdas.
  - `AuthenticationManager` desde `AuthenticationConfiguration`.
  - Permitir `/v3/api-docs/**` y `/swagger-ui/**`.
  - Manejar en `JwtFilterRequest` los tokens inválidos o expirados.
- [ ] **Paso 6: JWT moderno.**
  - jjwt 0.12.x (`jjwt-api`, `jjwt-impl` y `jjwt-jackson`).
  - Clave por variable de entorno, de al menos 256 bits.
  - `@ConfigurationProperties` con un record (`JwtProperties(String secret, Duration expiration)`).
  - `Instant` en lugar de `Date`.
- [ ] **Paso 7: Configuración y tests.**
  - Credenciales con `${DB_PASSWORD}`.
  - Arreglar el test con `@SpringBootTest`. Más adelante: `@DataJpaTest` + Testcontainers.
  - Opcional: renombrar `ProductRepostitory`.

**Termina la fase cuando:** `./mvnw clean verify` pasa, la app arranca, el login en `/auth/authenticate` funciona y se pueden probar los endpoints con el token desde Swagger UI.

## Progreso / notas de revisión

<!-- Registrar aquí cada paso revisado: fecha, resultado y pendientes. -->
- 2026-11-07: diagnóstico hecho y guía de la Fase 1 entregada. Pendiente: el usuario empieza el Paso 0.
