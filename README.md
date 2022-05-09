# jpush-react-native-extra

get jpush android intent data

## Installation

```sh
npm install jpush-react-native-extra
```

## Usage

```js
import { getExtras } from "jpush-react-native-extra";

// ...
getExtras()
  .then((data) => {
    if (data && data.n_extras) {
      const extras = JSON.parse(data.n_extras);
      if (parseInt(extras.push_type, 10) === 301) {
        navigate('ToolsRouter');
      }
    }
  })
  .catch((err) => {
    console.log(err);
  });
```

## Contributing

See the [contributing guide](CONTRIBUTING.md) to learn how to contribute to the repository and the development workflow.

## License

MIT
