<h1>Encriptador de arquivo.txt 📜</h1>

<h2>Definição 🗿</h2>
O encriptador transforma informações em códigos secretos (cifras),
que só podem ser lidas por quem possui as chaves para desencriptar os dados.<br><br>

<h2>Aplicações 📎</h2>

|  |  |
| ------: | -----------: |
| senhas | sistemas             |
|  dados | assinaturas digitais |
| arquivos | criptomoedas       |
<br>

<h2>Implementação 💭️ </h2>

O algoritmo essencial para essa implementação é o gerador de números 
pseudo-aleatórios de Lehmer (na classe "LCG.java"). Para cada caractere 
do arquivo é gerado um número aleatório, ao qual é armazenado em um arquivo e serão
como chaves para a descriptografia.<br>

**Exemplo de criptografia:**<br>
1°  decimalCaractereLido = X <br>
2°  aleatorioGerado = y <br>
3°  novoCaractere = decimalCaractereLido + aleatorioGerado<br>
4°  converte o novoCaractere em char e escreve no arquivo de criptografados<br>
5°  armazena o aleatorioGerado em um arquivo de aleatorios<br>

**Exemplo de descriptografia:**<br>
1°  decimalCaractereCriptografado = X<br>
2°  aleatorioUsado = Y<br>
3°  caractereOriginal = decimalCaractereCriptografado - aleatorioUsado<br>
4°  Converte o caractereOriginal em char e armazena no arquivo de descriptografados<br>

O arquivo de aleatórios deve ser guardado em total segurança, pois sem eles não é possível realizar
a descriptografia.<br><br>



<h2>Como usar 🤔</h2>

| **Objetivo** | **Onde** |
| ------: | -----------: |
| Executar ▶️| <kbd>"Main.java"</kbd> |
| Escrever a entrada ✍️| <kbd>Quando executar selecione um arquivo.txt</kbd>  |
| ver criptografado 🔣    | <kbd>"arquivos/saida/criptografado.txt"</kbd>    |
| ver descriptografado 📰️ | <kbd>"arquivos/saida/descriptografado.txt"</kbd> |
<br>
<h2>Exemplo </h2>

- Antes<br><br>
<img align="center" alt="image2" height="50" width="500" src="https://cdn.discordapp.com/attachments/816279312591880235/977680059420774410/Screenshot_2.png"><br><br>

- Depois<br><br>
<img align="center" alt="image1" height="60" width="500" src="https://cdn.discordapp.com/attachments/785634513966333953/990364793829482516/Screenshot_2.png"><br><br>

