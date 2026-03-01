# 🚀 Guía de Despliegue – PeakLog

Esta guía describe el flujo completo desde el desarrollo hasta la verificación del despliegue en el cluster Kubernetes.

---

## 🧑‍💻 1. Desarrollo

- Las pruebas se realizan en local.
- Se utiliza el archivo `.env` como fuente de variables de entorno.
- La aplicación se ejecuta con el perfil:

```bash
SPRING_PROFILES_ACTIVE=dev
```

- La base de datos utilizada en este entorno es PostgreSQL local (puerto 5433).

---

## 📤 2. Push a `main`

Una vez validados los cambios:

```bash
git add .
git commit -m "feat: descripción del cambio"
git push origin main
```

Al hacer push a la rama `main`:

- Se ejecuta automáticamente el workflow de GitHub Actions.
- Se construye el proyecto con Maven.
- Se genera la imagen Docker.
- La imagen se publica en Docker Hub:

```
joseacarmo17/peaklog:latest
```

---

## ☸ 3. Despliegue en Kubernetes

Una vez que la imagen está disponible en Docker Hub, se deben reiniciar los deployments para forzar la descarga de la nueva versión.

Ejecutar:

```bash
kubectl rollout restart deployment peaklog-deployment -n peaklog
kubectl rollout restart deployment postgres-deployment -n peaklog
```

Esto fuerza a Kubernetes a:

- Descargar la nueva imagen de la aplicación.
- Crear nuevos pods.
- Terminar los pods anteriores de forma controlada.
- Garantizar que tanto la aplicación como la base de datos estén actualizadas y sincronizadas.

---

## 🔎 4. Verificación del Despliegue

### Verificar estado de los pods

```bash
kubectl get pods -n peaklog
```

Todos los pods deben aparecer con estado:

```
Running
```

---

### Ver logs de la aplicación

```bash
kubectl logs -f deployment/peaklog-deployment -n peaklog
```

Permite verificar que la aplicación ha arrancado correctamente y que la conexión con la base de datos es correcta.

---

### Alternativa visual

También se puede utilizar:

```bash
k9s
```

Para tener visibilidad interactiva del estado de los pods, logs y recursos del namespace `peaklog`.
