1. ¿En qué consiste el principio de responsabilidad única? ¿Cuál es su propósito?
Significa que una clase deberia tener solamente un trabajo, y una única razón para ser modificada.
El propósito es que al momento de tener que modificar algun detalle, no sean necesarios cambios adicionales en otros archivos del proyecto.

2. ¿Qué características tiene, según su opinión, un “buen” código o código limpio?
Un código limpio o de calidad debe ser fácil de entender o intuir sin necesidad de muchos comentarios/documentaciones adicionales, debe ser fácil de mantener para cualquier miembro del equipo.

3. Detalla cómo harías todo aquello que no hayas llegado a completar.
Para los unit tests, creo que lo que realmente podriamos testear serian los useCases, esto sería, las llamadas a los apis, pero como android solo nos permite simular un mock server y no hacer las api calls de verdad, tengo que escribir manualmente los requests y responses que espero.
No los agregue porque estaba teniendo un error sobre clases "duplicadas en gradle", que creo son realmente por compatibilidad entre las versiones de las librerias de testing y hilt, que me estaba llevando tiempo solucionar, entonces para no tardar demasiado decidi enviar el challenge sin eso.