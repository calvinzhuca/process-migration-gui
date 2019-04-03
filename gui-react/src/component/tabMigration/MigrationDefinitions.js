import React, { Component } from "react";
import { Table } from "patternfly-react";
import { actionHeaderCellFormatter } from "patternfly-react";
import axios from "axios";
import validator from "validator";

import { Icon } from "patternfly-react";
import { MessageDialog } from "patternfly-react";

import {
  MockupData_Migrations_Definitions,
  MockupData_Migrations_Logs
} from "../common/MockupData";
import { BACKEND_URL, USE_MOCK_DATA } from "../common/PimConstants";
import PageViewMigrationLogs from "./PageViewMigrationLogs";
import PageEditMigrationDefinitionModal from "./PageEditMigrationDefinitionModal";

export default class MigrationDefinitions extends Component {
  constructor(props) {
    super(props);
    this.state = {
      migrationsDefinitions: [],
      migrationLogs: [],
      showLogDialog: false,
      showDeleteConfirmation: false,
      deleteMigrationId: "",
      editMigrationId: "",
      validationMessage: ""
    };
  }

  componentDidMount() {
    this.retriveMigrationDefinitions();
  }

  hideDetailDialog = () => {
    this.setState({
      showLogDialog: false
    });
  };

  retriveMigrationLogs = rowData => {
    this.setState({
      showLogDialog: true
    });
    if (USE_MOCK_DATA) {
      //console.log("retriveMigrationLogs use mock data: ");
      this.setState({
        migrationLogs: MockupData_Migrations_Logs
      });
    } else {
      const servicesUrl =
        BACKEND_URL + "/migrations/" + rowData.id + "/results";
      //console.log('retriveMigrationLogs url: ' + servicesUrl);
      axios.get(servicesUrl, {}).then(res => {
        const results = res.data;
        //console.log("retriveMigrationLogs " + JSON.stringify(results));
        this.setState({
          migrationLogs: results
        });
        //console.log("retriveMigrationLogs is done ");
      });
    }
  };

  showDeleteDialog = id => {
    this.setState({
      showDeleteConfirmation: true,
      deleteMigrationId: id
    });
  };

  hideDeleteDialog = () => {
    this.setState({
      showDeleteConfirmation: false
    });
  };

  deleteMigration = () => {
    if (USE_MOCK_DATA) {
      //console.log("deleteMigration use mock data: ");
      this.hideDeleteDialog();
      this.retriveMigrationDefinitions();
    } else {
      //need to create a temp variable "self" to store this, so I can invoke this inside axios call
      const self = this;
      const servicesUrl =
        BACKEND_URL + "/migrations/" + this.state.deleteMigrationId;
      //console.log("deleteMigration url: " + servicesUrl);
      axios.delete(servicesUrl, {}).then(() => {
        //const results = res.data;
        //console.log("deleteMigration " + JSON.stringify(results));
        self.hideDeleteDialog();
        self.retriveMigrationDefinitions();
      });
    }
  };

  retriveMigrationDefinitions = () => {
    const input = document.getElementById("id_migrationsDefinitions_input1");
    if (
      input != null &&
      input.value != null &&
      input.value != "" &&
      !validator.isNumeric(input.value)
    ) {
      //console.log("Error: migration id should be numeric");
      this.setState({
        validationMessage: "Error: migration id should be numeric"
      });
    } else {
      this.setState({
        validationMessage: ""
      });
      if (USE_MOCK_DATA) {
        //console.log("retriveMigrationDefinitions use mock data: ");
        const migrationsDefinitions = MockupData_Migrations_Definitions;
        this.setState({
          migrationsDefinitions
        });
      } else {
        if (input != null) {
          const serviceUrl = BACKEND_URL + "/migrations/" + input.value;
          axios.get(serviceUrl, {}).then(res => {
            var migrationsDefinitions = res.data;
            if (migrationsDefinitions != null) {
              const tmpStr = JSON.stringify(migrationsDefinitions);
              if (tmpStr != "" && tmpStr.charAt(0) != "[") {
                //this is single element json, need to change to json array, otherwise the table won't display
                migrationsDefinitions = [migrationsDefinitions];
              }
            }
            //console.log("response: " + JSON.stringify(migrationsDefinitions));
            this.setState({
              migrationsDefinitions
            });
          });
        }
      }
    }
  };

  render() {
    const headerFormat = value => <Table.Heading>{value}</Table.Heading>;
    const cellFormat = value => <Table.Cell>{value}</Table.Cell>;

    const resultBootstrapColumns = [
      {
        header: {
          label: "ID",
          formatters: [headerFormat]
        },
        cell: {
          formatters: [cellFormat]
        },
        property: "id"
      },
      {
        header: {
          label: "Status",
          formatters: [headerFormat]
        },
        cell: {
          formatters: [
            (value, { rowData }) => [
              <DisplayStatus
                key="0"
                rowData={rowData}
                retriveMigrationLogs={this.retriveMigrationLogs}
              />
            ]
          ]
        },
        property: "status"
      },
      {
        header: {
          label: "Created At",
          formatters: [headerFormat]
        },
        cell: {
          formatters: [cellFormat]
        },
        property: "createdAt"
      },
      {
        header: {
          label: "Started At",
          formatters: [headerFormat]
        },
        cell: {
          formatters: [cellFormat]
        },
        property: "startedAt"
      },
      {
        header: {
          label: "Finished At",
          formatters: [headerFormat]
        },
        cell: {
          formatters: [cellFormat]
        },
        property: "finishedAt"
      },
      {
        header: {
          label: "Scheduled At",
          formatters: [headerFormat]
        },
        cell: {
          formatters: [
            (value, { rowData }) => [
              <Table.Cell key="0">
                {rowData.definition.execution.scheduledStartTime}
              </Table.Cell>
            ]
          ]
        },
        property: "definition.planId"
      },
      {
        header: {
          label: "Error Message",
          formatters: [headerFormat]
        },
        cell: {
          formatters: [cellFormat]
        },
        property: "errorMessage"
      },
      {
        header: {
          label: "Actions",
          props: {
            rowSpan: 1,
            colSpan: 2
          },
          formatters: [actionHeaderCellFormatter]
        },
        cell: {
          formatters: [
            (value, { rowData }) => [
              <Table.Actions key="0">
                <Table.Button
                  bsStyle="link"
                  onClick={() => this.showDeleteDialog(rowData.id)}
                >
                  <Icon type="fa" name="trash" />
                </Table.Button>
              </Table.Actions>,
              <DisplayActions
                key="1"
                rowData={rowData}
                openEditMigration={this.openEditMigration}
              />
            ]
          ]
        },
        property: "action"
      }
    ];

    //for View migration logs pop-up
    const primaryContent = (
      <PageViewMigrationLogs migrationLogs={this.state.migrationLogs} />
    );
    const secondaryContent = <p />;
    const icon = <Icon type="pf" name="info" />;

    //for Delete migration definition pop-up
    const primaryDeleteContent = (
      <p className="lead">
        Please confirm you will delete this migration:{" "}
        {this.state.deleteMigrationId}
      </p>
    );
    const deleteIcon = <Icon type="pf" name="error-circle-o" />;

    //only for status is "SCHEDULED" enable the "Edit" button
    function DisplayActions(props) {
      const rowData = props.rowData;
      if (rowData.status == "SCHEDULED") {
        return (
          <Table.Actions key="1">
            <PageEditMigrationDefinitionModal rowData={rowData} />
          </Table.Actions>
        );
      } else {
        return <Table.Actions key="1" />;
      }
    }

    //for status other than "SCHEDULED" enable the link to check migration logs
    function DisplayStatus(props) {
      const rowData = props.rowData;
      if (rowData.status == "SCHEDULED") {
        return <Table.Cell key="0">{rowData.status}</Table.Cell>;
      } else {
        return (
          <Table.Cell key="0">
            <a href="#" onClick={() => props.retriveMigrationLogs(rowData)}>
              {rowData.status}
            </a>
          </Table.Cell>
        );
      }
    }

    return (
      <div>
        {/* View migration logs pop-up */}
        <MessageDialog
          show={this.state.showLogDialog}
          onHide={this.hideDetailDialog}
          primaryAction={this.hideDetailDialog}
          primaryActionButtonContent="Close"
          title="View Migration Logs"
          icon={icon}
          primaryContent={primaryContent}
          secondaryContent={secondaryContent}
          accessibleName="viewMigrationLogsDialog"
          accessibleDescription="migrationDetailDialogContent"
          className="kie-pim-dialog--wide"
        />

        {/* Delete migration definition pop-up */}
        <MessageDialog
          show={this.state.showDeleteConfirmation}
          onHide={this.hideDeleteDialog}
          primaryAction={this.deleteMigration}
          secondaryAction={this.hideDeleteDialog}
          primaryActionButtonContent="Delete"
          secondaryActionButtonContent="Cancel"
          primaryActionButtonBsStyle="danger"
          title="Delete Migration Definition"
          icon={deleteIcon}
          primaryContent={primaryDeleteContent}
          accessibleName="deleteConfirmationDialog"
          accessibleDescription="deleteConfirmationDialogContent"
        />

        <br />
        <div className="row">
          <div className="col-xs-12">
            <input
              id="id_migrationsDefinitions_input1"
              type="search"
              placeholder="Search By Migration ID"
            />
            <button type="button" onClick={this.retriveMigrationDefinitions}>
              <span className="fa fa-search" />
            </button>
            {this.state.validationMessage}
          </div>
        </div>
        <br />
        <div className="row">
          <div className="col-xs-12">
            <Table.PfProvider striped columns={resultBootstrapColumns}>
              <Table.Header />
              <Table.Body rows={this.state.migrationsDefinitions} rowKey="id" />
            </Table.PfProvider>
          </div>
        </div>
      </div>
    );
  }
}
