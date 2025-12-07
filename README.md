# Automatizaci-n-Pruebas-E2E

Automatización UI con Serenity BDD (Screenplay) y Cucumber para el flujo principal de creación y validación de empleados en [OrangeHRM](https://opensource-demo.orangehrmlive.com/).

## Requisitos
- Java 17
- Maven 3.9+
- Google Chrome instalado para ejecución local

## Ejecución
### Local
```bash
mvn clean verify -Denvironment=default
```

### BrowserStack
Configura `BROWSERSTACK_USERNAME` y `BROWSERSTACK_ACCESS_KEY` y ejecuta:
```bash
mvn clean verify -Denvironment=browserstack
```

### Variables útiles
- Archivo `src/test/resources/config/users.yml`: mantiene usuarios por defecto.
- `src/test/resources/config/browserstack.yml`: plantilla para capacidades remotas.
- `src/test/resources/config/jira.yml`: espacio para credenciales de Jira si se desea reportar bugs desde la ejecución.
- Para la foto de perfil si no existe la ruta solicitada (por defecto `data/alex-photo.png`) se genera
  automáticamente un placeholder en `target/test-images/`. Puedes reemplazarlo por cualquier imagen local o remota accesible
  desde el runner.

## Reportes
Serenity genera el reporte en `target/site/serenity/index.html` con evidencia (pantallas después de cada paso).

## CI
El pipeline en `.github/workflows/tests.yml` separa ejecuciones locales y en BrowserStack (si existen secretos configurados).

