# jpush-react-native-extra

get jpush android intent data

## Installation

```sh
npm install jpush-react-native-extra
```

Add to MainActivity.java

```java
@Override
public void onNewIntent(Intent intent) {
    super.onNewIntent(intent);
    //如果该方法已被重写, 那么添加 setIntent(intent); 这行就可以
    setIntent(intent);
}
```

## Usage

```js
import { getExtras } from 'jpush-react-native-extra';

// ...
if (Platform.OS === 'android') {
  setTimeout(() => {
    getExtras()
      .then((data) => {
        console.log('Extras:', data);
        if (data && data.n_extras) {
          const extras = JSON.parse(data.n_extras);
          console.log(extras);
          if (parseInt(extras.push_type, 10) === 301) {
            navigate('ToolsRouter');
          }
        }
      })
      .catch((err) => {
        console.log(err);
      });
  }, 1000);
}
```

## Contributing

See the [contributing guide](CONTRIBUTING.md) to learn how to contribute to the repository and the development workflow.

## License

MIT
