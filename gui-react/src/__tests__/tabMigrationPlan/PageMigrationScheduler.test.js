import renderer from "react-test-renderer";
import React from "react";
import PageMigrationScheduler from "../../component/tabMigrationPlan/wizardExecuteMigration/PageMigrationScheduler";

test("PageMigrationScheduler renders correctly using snapshot", () => {
  const myMock = jest.fn();
  const tree = renderer
    .create(
      <PageMigrationScheduler
        setCallbackUrl={myMock}
        setScheduleStartTime={myMock}
      />
    )
    .toJSON();
  expect(tree).toMatchSnapshot();
});
