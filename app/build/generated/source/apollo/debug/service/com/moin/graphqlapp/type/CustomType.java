// AUTO-GENERATED FILE. DO NOT MODIFY.
//
// This class was automatically generated by Apollo GraphQL plugin from the GraphQL queries it found.
// It should not be modified by hand.
//
package com.moin.graphqlapp.type;

import com.apollographql.apollo.api.ScalarType;
import java.lang.Override;
import java.lang.String;

public enum CustomType implements ScalarType {
  ID {
    @Override
    public String typeName() {
      return "ID";
    }

    @Override
    public String className() {
      return "java.lang.String";
    }
  },

  BIGINT {
    @Override
    public String typeName() {
      return "bigint";
    }

    @Override
    public String className() {
      return "java.lang.Object";
    }
  }
}
