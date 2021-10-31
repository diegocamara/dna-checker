package com.checker.dna.extensions;

import org.junit.jupiter.api.extension.AfterEachCallback;
import org.junit.jupiter.api.extension.BeforeAllCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.persistence.EntityManager;

public class ClearDatabaseExtension implements BeforeAllCallback, AfterEachCallback {

  private EntityManager entityManager;

  @Override
  public void beforeAll(ExtensionContext extensionContext) throws Exception {
    final var applicationContext = SpringExtension.getApplicationContext(extensionContext);
    this.entityManager =
        applicationContext
            .getBean(EntityManager.class)
            .getEntityManagerFactory()
            .createEntityManager();
  }

  @Override
  public void afterEach(ExtensionContext extensionContext) throws Exception {
    clearDatabase();
  }

  private void clearDatabase() {
    final var transaction = this.entityManager.getTransaction();
    transaction.begin();
    entityManager.createNativeQuery("DELETE FROM DNA_TB;").executeUpdate();
    transaction.commit();
  }
}
