# Binance java client

### Content
* [Binance docs](#binance-docs)
* [Why to create binance client](#why-to-create-binance-client)
* [Project description](#project-description)
* [Environment variables](#environment-variables)

### Binance docs
* [binance spot](https://binance-docs.github.io/apidocs/spot/en/#general-info)
* [binance futures](https://binance-docs.github.io/apidocs/futures/en/#general-info)

### Why to create binance client
If you look into [binance API docs](https://binance-docs.github.io/apidocs/futures/en/#general-info) you will notice the following disclaimer 
```
Disclaimer:
The following SDKs are provided by partners and users, and are not officially produced. They are only used to help users become familiar with the API endpoint. Please use it with caution and expand R&D according to your own situation.
Binance does not make any commitment to the safety and performance of the SDKs, nor will be liable for the risks or even losses caused by using the SDKs.
```
The reason behind is pretty simple. It's hard to maintain up-to-date libraries, and it may take time to rewrite library after you change your API. So libraries may have outdated code, where API has been changed, but library is not up-to-date with recent API changes. Thia means, that there is a risk that such a library, if used, may cause some error which may bring financial losses (for example you send `0,3` but they changed it to `0.3` and now your comma would be cancelled and you send request to buy 3 bitcoins instead of `0.3`. Which may not be your desire, but if you have enough balance such order would be executed). That's why sometimes it may be preferable to use raw API, and write your own wrapper with `feign` on top of such raw API.

### Project description
This is not end-to-end library to work with binance spot & futures API. Yet it may be a good starting point, if you want to build integration with binance, but don't want to use any existing libraries. In this case using this project as a backbone is a very good idea.
Here I just use wrapper [feign client](https://github.com/dgaydukov/binance-client/tree/main/src/main/java/com/example/binance/feign) on top of raw binance API, and have ready-to-use integration out-of-the-box. I've implemented a few basic API endpoints, but you can add as many as you want. This project is a backbone with auth layer, and you can easily extend it by adding API endpoints you want to call.

### Environment variables
To run the project you need to add following env variables. In the example below I use `binance testnet`:
```
SPOT_BASE_URL=https://testnet.binance.vision;
SPOT_API_KEY={you API key for spot};
SPOT_SECRET_KEY={you secret key for spot};
FUTURES_BASE_URL=https://testnet.binancefuture.com;
FUTURES_API_KEY={you API key for futures};
FUTURES_SECRET_KEY={you secret key for futures};
```