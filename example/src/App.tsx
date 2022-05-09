import React, { useEffect } from 'react';
import { StyleSheet, View, Text } from 'react-native';
import { getExtras } from 'jpush-react-native-extra';

export default function App() {
  useEffect(() => {
    getExtras()
      .then((data: any) => {
        if (data && data.n_extras) {
          const extras = JSON.parse(data.n_extras);
          console.log(extras);
        }
      })
      .catch((err: any) => {
        console.log(err);
      });
  }, []);

  return (
    <View style={styles.container}>
      <Text>Result</Text>
    </View>
  );
}

const styles = StyleSheet.create({
  container: {
    flex: 1,
    alignItems: 'center',
    justifyContent: 'center',
  },
  box: {
    width: 60,
    height: 60,
    marginVertical: 20,
  },
});
