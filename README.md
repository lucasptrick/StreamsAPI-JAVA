# Streams
Execício de introdução ao paradigma de programação funcional. Surgida após atualização da versão da linguagem Java. 

>> Streams no Java8 são uma parte poderosa e fundamental da biblioteca. Eles permitem manipular sequências de elementos de forma declarativa e concisa. Eles fornecem uma abstração de alto nível que permite procesar e operar em dados de maneira semelhante a ppelines de processamento.

>> Principais características das Streams do Java 8:
*Processamento Declarativo:* Com Streams, você pode expressar operações de processamento de dados de forma declarativa, descrevendo o que deseja fazer com os dados, em vez de como fazê-lo. Isso leva a um código mais limpo e legível.
*Pipeline de Operações:* As operações em Streams são encadeadas em uma sequência chamada pipeline. Cada operação é executada em série, o que facilita a composição de várias operações para processar os dados.
*Operações Intermediárias e Terminais:* As operações em uma pipeline de Stream podem ser intermediárias ou terminais. Operações intermediárias são aquelas que retornam outro Stream, permitindo encadear várias operações. Operações terminais produzem um resultado final ou um valor, encerrando a pipeline.
*Lazy Evaluation:* As Streams têm avaliação preguiçosa. Isso significa que as operações são executadas somente quando o resultado é realmente necessário. Isso permite otimizações e economia de recursos.
*Não Destrutivas (Non-destructive):* As Streams não modificam os dados originais. Em vez disso, elas criam novos Streams que representam os dados transformados.
*Parallel Streams:* Java Streams também suporta operações paralelas. Isso significa que é possível executar operações em paralelo para processar grandes conjuntos de dados de maneira eficiente, aproveitando o poder do processamento multi-core.
*Compatibilidade com Coleções:* Streams podem ser criadas a partir de coleções como Listas e Sets, bem como outras fontes como arrays, arquivos e fluxos infinitos.

Streams no Java 8 trouxeram uma abordagem mais funcional e orientada a expressões para o processamento de dados, o que pode levar a códigos mais concisos, legíveis e, em alguns casos, mais eficientes. Eles são particularmente úteis para operações de filtro, mapeamento, redução e transformação em conjuntos de dados.
