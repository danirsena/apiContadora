# 📊 API Contadora

> ✅ **Status:** Online  📦 **Versão:** 1.0.0  🌐 **Base URL:** [https://apicontadora.onrender.com](https://apicontadora.onrender.com)

---

## 💡 O que é uma API

Uma **API (Application Programming Interface)** é um conjunto de endpoints e regras que permitem que sistemas diferentes se comuniquem entre si.  
De forma simples, é como um *cardápio* de funcionalidades que o servidor oferece para que outros programas possam usá-las — como sites, apps ou serviços externos.  

Essas interações geralmente acontecem por meio de **requisições HTTP** (`GET`, `POST`, `PUT`, `DELETE`), que retornam informações em formato **JSON** ou texto simples.

---

## 🧮 O que ESSA API faz

A **API Contadora** tem como objetivo **registrar e contabilizar o número de vezes que um link é acessado**.

1. Primeiro, um link é criado (somente com acesso autorizado).  
2. Depois, cada clique feito na rota abaixo redireciona o usuário para o site original **e incrementa o contador** do link:


> ⚠️ **Importante:**  
> Esta API **não possui ligação direta com os sites cadastrados** e **não se responsabiliza** pelo conteúdo acessado.  
> Nenhum dado sensível do usuário é armazenado — apenas a **data e hora de cada clique**.

---

## 🧭 Funções / Rotas

### 🌍 Livres para acesso de todos

#### 🔗 Classe `Link`

1. **sayHello**  
   🔗 [https://apicontadora.onrender.com/link/sayHello](https://apicontadora.onrender.com/link/sayHello)  
   Retorna a string `"Hello!"`.

---

2. **[PRINCIPAL] Abrir link**  
   🔗 [https://apicontadora.onrender.com/link/abrirLink/${id}](https://apicontadora.onrender.com/link/abrirLink/1)  
   Redireciona o usuário para o link cadastrado e adiciona +1 ao contador de acessos.  

   Exemplo:  
   `https://apicontadora.onrender.com/link/abrirLink/1`

---

3. **GET todos os links**  
   🔗 [https://apicontadora.onrender.com/link/getUrls/short](https://apicontadora.onrender.com/link/getUrls/short)  
   Retorna um JSON com:
   - ID  
   - URL  
   - Contagem de cliques  
   - Lista com os `LocalDateTime` (data e hora) dos cliques  

---

4. **GET link específico**  
   🔗 [https://apicontadora.onrender.com/link/getUrl/${idEspecífico}](https://apicontadora.onrender.com/link/getUrl/1)  
   Retorna os dados do link solicitado, incluindo:
   - ID  
   - URL  
   - Contagem  
   - Histórico de cliques  

   Exemplo:  
   `https://apicontadora.onrender.com/link/getUrl/1`

---

#### 🖱️ Classe `Click`

1. **sayHello**  
   🔗 [https://apicontadora.onrender.com/click/sayHello](https://apicontadora.onrender.com/click/sayHello)  
   Retorna a string `"Hello!"`.

---

2. **GET todos os cliques**  
   🔗 [https://apicontadora.onrender.com/click/getClicks](https://apicontadora.onrender.com/click/getClicks)  
   Retorna um JSON com todos os cliques registrados:
   - ID  
   - Data do clique (`aaaa-mm-ddThh:mm:ss:ms`)  
   - URL redirecionada  

---

3. **GET clique específico**  
   🔗 [https://apicontadora.onrender.com/click/getClick/${idEspecífico}](https://apicontadora.onrender.com/click/getClick/18)  
   Retorna os dados de um clique específico.  

   Exemplo:  
   `https://apicontadora.onrender.com/click/getClick/18`

---

### 🔒 Somente com acesso autorizado (chave de API)

> 🧰 **Antes de começar:**  
> Recomenda-se o uso de ferramentas como **HTTPie**, **Postman** ou similares para enviar os dados necessários (atributos de criação e chave de API).

#### 🔗 Classe `Link`

1. **POST (Criar link)**  
   🔗 [https://apicontadora.onrender.com/link/addUrl](https://apicontadora.onrender.com/link/addUrl)  
   Cria um novo link.  
   Requer um JSON no corpo da requisição (`RequestBody`) com:  
   - `url` que se deseja registrar  

---

2. **PUT (Alterar link)**  
   🔗 [https://apicontadora.onrender.com/link/updateUrlLink/${idEspecífico}](https://apicontadora.onrender.com/link/updateUrlLink/87)  
   Atualiza a URL de um link existente.  

   Exemplo:  
   `https://apicontadora.onrender.com/link/updateUrlLink/87`

---

3. **Zerar contador do link**  
   🔗 [https://apicontadora.onrender.com/link/zerarContador/${idEspecífico}](https://apicontadora.onrender.com/link/zerarContador/87)  
   Faz o contador do link especificado voltar para `0`.

---

4. **DELETE link**  
   🔗 [https://apicontadora.onrender.com/link/deleteUrl/${idEspecífico}](https://apicontadora.onrender.com/link/deleteUrl/87)  
   Exclui completamente o link e seus dados associados:
   - ID  
   - URL  
   - Contagem de cliques  
   - Histórico de cliques  

---

#### 🖱️ Classe `Click`

1. **DELETE clique**  
   🔗 [https://apicontadora.onrender.com/click/deleteClick/${idEspecífico}](https://apicontadora.onrender.com/click/deleteClick/87)  
   Exclui um clique específico, sem afetar o link original.  

   Exemplo:  
   `https://apicontadora.onrender.com/click/deleteClick/87`

---

<br>

### Feito com muito ❤️ e 🎵 por **danirsena**
